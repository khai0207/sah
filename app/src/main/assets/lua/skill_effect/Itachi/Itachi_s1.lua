--v3
--[[
Itachi
0
0
penxue
2
{default,qianjin,1,0,0,1,1.000000}{default,gongji2,1,0,2,44,1.000000}
1
{default,-400.000000,0.000000,-400.000000,0.000000,1,1,0,1,0.000000,0,0,0,0}
0

0

1
{0,Itachi,0,0,0.000000,0.000000,49,0}
0

0

0

0


daiji
]]--
local skillTest = {
attack_type       = "jingong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,1,1},{"gongji2",1.000000,2,44,1}}, 
pos_sequence      = {{-400.000000,0.000000,-400.000000,0.000000,1,1,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"Itachi",0,0,0.000000,0.000000,{},{},{},{},0}}, 
bloodNum          = 7,
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
