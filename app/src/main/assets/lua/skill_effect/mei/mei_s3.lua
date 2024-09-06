--v3
--[[
mei
1
0
penxue
1
{default,gongji4,1,0,0,167,1.000000}
1
{default,0.000000,0.000000,0.000000,0.000000,0,0,0,4,0.000000,0,0,0,0}
0

0

1
{0,mei,2,0,0.000000,0.000000,171,0}
2
{delay,,0,0,0,3,0.000000}{default,gongji4_texiao1,1,0,4,171,1.000000}
0

0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji4",1.000000,0,167,1}}, 
pos_sequence      = {{0.000000,0.000000,0.000000,0.000000,0,0,0,4,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"mei",2,0,0.000000,0.000000,{{"",0.000000,0,3,0},{"gongji4_texiao1",1.000000,4,171,1}},{},{},{},0}}, 
bloodNum          = 5,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
