--v3
--[[
luffy new
1
0
penxue
3
{default,qianjin,0,0,0,4,1.000000}{default,gongji4,1,0,5,32,1.000000}{default,gongji3,1,0,33,73,1.000000}
1
{delay,0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}
0

2
{delay,0.000000,2,0,3}{default,0.000000,0,4,5}
0

daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,4,0},{"gongji4",1.000000,5,32,1},{"gongji3",1.000000,33,73,1}}, 
pos_sequence      = {{0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {{0.000000,2,0,3},{0.000000,0,4,5}}, 
other_sequence      = {}, 
bloodNum          = 6,
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
